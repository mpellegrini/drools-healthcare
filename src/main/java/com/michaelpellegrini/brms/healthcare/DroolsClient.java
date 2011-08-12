package com.michaelpellegrini.brms.healthcare;

import java.util.Collection;

import javax.measure.Measure;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.SequentialOption;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import com.michaelpellegrini.brms.healthcare.fact.type.Gender;
import com.michaelpellegrini.brms.healthcare.fact.type.Height;
import com.michaelpellegrini.brms.healthcare.fact.type.WaistCircumference;
import com.michaelpellegrini.brms.healthcare.fact.type.Weight;
import com.michaelpellegrini.brms.healthcare.fact.value.GenderConstraint;


/**
 * This is a sample class to launch a rule base.
 */
public class DroolsClient {

	public static final void main(String[] args) {
		KnowledgeRuntimeLogger logger = null;
		StatefulKnowledgeSession ksession = null;
		
		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			ksession = kbase.newStatefulKnowledgeSession();
			logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "drools-playground");

			/*
			 * Create the asserted facts about a member and insert them
			 * into the knowledge session
			 */
			Height height = new Height(Measure.valueOf(180.0f, SI.CENTIMETER));
			Weight weight = new Weight(Measure.valueOf(104.0f, SI.KILOGRAM));
			WaistCircumference waistCircumference = new WaistCircumference(Measure.valueOf(38.0f, NonSI.INCH));
			Gender gender = new Gender(GenderConstraint.MALE);
			
			ksession.insert(height);
			ksession.insert(weight);
			ksession.insert(waistCircumference);
			ksession.insert(gender);
			
			/*
			 * Perform rule evaluation
			 */
			printFacts("Before fireAllRules", ksession);
			ksession.fireAllRules();
			printFacts("After First fireAllRules", ksession);
			
			
			/*
			 * Update the asserted fact Height to a different measurement
			 * and let the knowledge session know about it via an update
			 * and then perform another rule evaluation
			 */
			FactHandle fh = ksession.getFactHandle(height);
			Height modHeight = new Height(Measure.valueOf(76.0f, NonSI.INCH));
			ksession.update(fh, modHeight); 
			ksession.fireAllRules();
			printFacts("After Second fireAllRules", ksession);
			
			/*
			 * Now retract the asserted fact Height and perform another
			 * rule evaluation
			 */
			ksession.retract(fh);
			printFacts("After Retraction of Height fact", ksession);
			
		} finally {
			if (logger != null) {
				logger.close();
			}
			if (ksession != null) {
				ksession.dispose();
			}
		}
	}

	private static void printFacts(String msg, StatefulKnowledgeSession ksession) {
		System.out.println();
		System.out.println("###############################");
		System.out.println(msg);
		System.out.println("The Knowledge Session has the following " + ksession.getObjects().size() + " fact(s):");
		for (Object fact : ksession.getObjects()) {
			System.out.println("   * " + fact);
		}
		System.out.println();
	}

	private static KnowledgeBase readKnowledgeBase() throws IllegalArgumentException {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource changeset = ResourceFactory.newClassPathResource("change-set.xml");
		kbuilder.add(changeset, ResourceType.CHANGE_SET);
		
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(changeset.toString());
				System.err.println(error.getMessage());
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBaseConfiguration kbaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		kbaseConfig.setOption(SequentialOption.NO);
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbaseConfig);
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		

		// Prints out KnowledgeBase contents
		Collection<KnowledgePackage> pkgs = kbase.getKnowledgePackages();
		for (KnowledgePackage pkg : pkgs) {
			System.err.println("* " + pkg.getName());
			Collection<Rule> rules = pkg.getRules();
			for (Rule rule : rules) {
				System.err.println("   - " + rule.getName());
			}
		}
		
		System.err.println("*********************************");

		return kbase;
	}
}
