package org.axdt.as3.ui.contentassist.antlr;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.UnorderedGroup;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.LookAheadTerminal;
import org.eclipse.xtext.ui.editor.contentassist.antlr.ObservableXtextTokenStream;
import org.eclipse.xtext.ui.editor.contentassist.antlr.ObservableXtextTokenStream.StreamListener;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

/**
 * This parser class is a provisional fix for an issue described at
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=320941 - mb0
 * 
 * The code is a copy of
 * org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser
 * 
 * @author Sebastian Zarnekow
 */
public class As3ParserFix extends As3Parser {

	public Collection<FollowElement> getFollowElements(FollowElement element) {
		if (element.getLookAhead() <= 1)
			throw new IllegalArgumentException(
					"lookahead may not be less than or equal to 1");
		Collection<FollowElement> result = new ArrayList<FollowElement>();
		for (AbstractElement elementToParse : getElementsToParse(element)) {
			String ruleName = getRuleName(elementToParse);
			String[][] allRuleNames = getRequiredRuleNames(ruleName,
					elementToParse);
			for (String[] ruleNames : allRuleNames) {
				for (int i = 0; i < ruleNames.length; i++) {
					AbstractInternalContentAssistParser parser = createParser();
					parser.setUnorderedGroupHelper(getUnorderedGroupHelper()
							.get());
					final Iterator<LookAheadTerminal> iter = element
							.getLookAheadTerminals().iterator();
					ObservableXtextTokenStream tokens = new ObservableXtextTokenStream(
							new TokenSource() {
								public Token nextToken() {
									if (iter.hasNext()) {
										LookAheadTerminal lookAhead = iter.next();
										return lookAhead.getToken();
									}
									return Token.EOF_TOKEN;
								}
							}, parser);
					parser.setTokenStream(tokens);
					tokens.setListener(parser);
					parser.getGrammarElements().addAll(element.getTrace());
					parser.getGrammarElements().add(elementToParse);
					parser.getLocalTrace().addAll(element.getLocalTrace());
					parser.getLocalTrace().add(elementToParse);
					Collection<FollowElement> elements = getFollowElements(
							parser, elementToParse, ruleNames, i);
					result.addAll(elements);
				}
			}
		}
		return result;
	}

	private Collection<FollowElement> getFollowElements(
			final AbstractInternalContentAssistParser parser,
			AbstractElement elementToParse, String[] ruleNames, int startIndex) {
		try {
			final boolean[] wasEof = new boolean[] { false };
			final boolean[] announcedEofWithLA = new boolean[] { false };
			final boolean[] consumedSomething = new boolean[] { true };
			final boolean[] isRecovering = new boolean[] { false };
			ObservableXtextTokenStream stream = (ObservableXtextTokenStream) parser
					.getTokenStream();
			stream.setListener(new StreamListener() {
				public void announceEof(int lookAhead) {
					if (!isRecovering[0]) {
						parser.announceEof(lookAhead);
						if (lookAhead > 1)
							announcedEofWithLA[0] = true;
					}
					wasEof[0] = true;
				}

				public void announceConsume() {
					parser.announceConsume();
					if (!isRecovering[0])
						consumedSomething[0] = true;
				}

				public void announceMark(int marker) {
					parser.announceMark(marker);
				}

				public void announceRewind(int marker) {
					parser.announceRewind(marker);
				}
			});
			parser.setRecoveryListener(new AbstractInternalContentAssistParser.RecoveryListener() {
				public void endErrorRecovery() {
					isRecovering[0] = false;
				}

				public void beginErrorRecovery() {
					isRecovering[0] = true;
				}
			});
			int i = startIndex;
			Collection<FollowElement> result = null;
			while (i < ruleNames.length && !wasEof[0] && consumedSomething[0]) {
				consumedSomething[0] = false;
				announcedEofWithLA[0] = false;
				isRecovering[0] = false;
				Method method = parser.getClass().getDeclaredMethod(
						ruleNames[i]);
				method.setAccessible(true);
				method.invoke(parser);
				result = parser.getFollowElements();
				if (i == ruleNames.length - 1
						&& !GrammarUtil.isMultipleCardinality(elementToParse)) {
					if (consumedSomething[0] || announcedEofWithLA[0])
						return result;
					return Collections.emptyList();
				}
				if (!wasEof[0] && ruleNames.length != 1)
					i++;
				if (ruleNames.length > 2)
					throw new IllegalArgumentException(
							"The following lines assume that we have at most two rules to call.");
				int lastGrammarElement = parser.getGrammarElements().size() - 1;
				if (parser.getGrammarElements().get(lastGrammarElement) != elementToParse)
					throw new IllegalStateException(
							"Stack of grammar elements seems to be corrupt.");
				parser.getGrammarElements().remove(lastGrammarElement);
				int lastLocalTrace = parser.getLocalTrace().size() - 1;
				if (lastLocalTrace != -1) {
					parser.getLocalTrace().remove(lastLocalTrace);
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String[][] getRequiredRuleNames(String ruleName,
			AbstractElement elementToParse) {
		if (ruleName == null) {
			if (elementToParse instanceof RuleCall)
				return new String[][] { { "rule"
						+ ((RuleCall) elementToParse).getRule().getName() } };
			else
				return new String[0][];
		}
		if (!(GrammarUtil.isOptionalCardinality(elementToParse) || GrammarUtil
				.isOneOrMoreCardinality(elementToParse)))
			return new String[][] { { ruleName } };
		if ((elementToParse.eContainer() instanceof Group)) {
			List<AbstractElement> tokens = ((Group) elementToParse.eContainer())
					.getElements();
			int idx = tokens.indexOf(elementToParse) + 1;
			if (idx != tokens.size()) {
				String secondRule = getRuleName((AbstractElement) elementToParse
						.eContainer());
				secondRule = secondRule.substring(0,
						secondRule.lastIndexOf('_') + 1)
						+ idx;
				if (GrammarUtil.isMultipleCardinality(elementToParse))
					return new String[][] { { ruleName },
							{ ruleName, secondRule } };
				return new String[][] { { ruleName, secondRule } };
			}
		}
		return new String[][] { { ruleName } };
	}

	private Collection<AbstractElement> getElementsToParse(FollowElement element) {
		AbstractElement root = element.getGrammarElement();
		return getElementsToParse(root);
	}

	private Collection<AbstractElement> getElementsToParse(AbstractElement root) {
		if (root instanceof Alternatives || root instanceof UnorderedGroup)
			return ((CompoundElement) root).getElements();
		return Collections.singleton(root);
	}
}