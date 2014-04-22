package com.michaelpellegrini.drools.healthcare;

import com.michaelpellegrini.drools.healthcare.fact.type.Gender;
import com.michaelpellegrini.drools.healthcare.fact.type.Height;
import com.michaelpellegrini.drools.healthcare.fact.type.WaistCircumference;
import com.michaelpellegrini.drools.healthcare.fact.type.Weight;
import com.michaelpellegrini.drools.healthcare.fact.value.GenderConstraint;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import javax.measure.Measure;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import java.util.Collection;


/**
 * This is a sample class to launch a rule base.
 */
public class DroolsClient {

    public static final void main(String[] args) {
        KieSession ksession = null;

        try {
            // load up the knowledge base
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kcontainer = kieServices.getKieClasspathContainer();

            KieBase kbase = kcontainer.getKieBase("drools-healthcare");

            ksession = kcontainer.newKieSession("stateful-session");

            printRules(kbase);

			/*
             * Create the asserted facts about a member and insert them
			 * into the knowledge session
			 */
            Height height = new Height(Measure.valueOf(180.0f, SI.CENTIMETRE));
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
            printFacts("Before first fireAllRules", ksession);
            ksession.fireAllRules();
            printFacts("After first fireAllRules", ksession);

			/*
			 * Update the asserted fact Height to a different measurement
			 * and let the knowledge session know about it via an update
			 * and then perform another rule evaluation
			 */
            FactHandle fh = ksession.getFactHandle(height);
            Height modHeight = new Height(Measure.valueOf(76.0f, NonSI.INCH));
            ksession.update(fh, modHeight);
            ksession.fireAllRules();
            printFacts("After second fireAllRules (Updated Height fact)", ksession);
			
			/*
			 * Now delete the asserted fact Height and perform another
			 * rule evaluation
			 */
            ksession.delete(fh);
            printFacts("After third fireAllRules (Deletion of Height fact)", ksession);

        } finally {
            if (ksession != null) {
                ksession.dispose();
            }
        }
    }

    private static void printFacts(String msg, KieSession ksession) {
        System.out.println();
        System.out.println("###############################");
        System.out.println(msg);
        System.out.println("The Knowledge Session has the following " + ksession.getObjects().size() + " fact(s):");
        for (Object fact : ksession.getObjects()) {
            System.out.println("   * " + fact);
        }
        System.out.println();
    }

    private static void printRules(KieBase kieBase) {
        // Prints out KnowledgeBase contents
        Collection<KiePackage> pkgs = kieBase.getKiePackages();;
        for (KiePackage pkg : pkgs) {
            System.err.println("* " + pkg.getName());

            Collection<Rule> rules = pkg.getRules();
            for (Rule rule : rules) {
                System.err.println("   - " + rule.getName());
            }
        }
        System.err.println("*********************************");
    }
}
