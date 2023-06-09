package a2_2101040052;

import static utils.TextIO.getln;
import static utils.TextIO.putln;
import static utils.TextIO.writeFile;
import static utils.TextIO.writeStandardOutput;

import java.util.Scanner;
import java.util.Vector;

import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview PCProg is a program that captures data about PC objects and
 *           displays a report about them on the console.
 * 
 * @attributes objs Set<PC>
 * 
 * @object A typical PCProg is {c1,...,cn} where c1,...,cn are pcs
 * 
 * @abstract_properties mutable(objs)=true /\ optional(objs)=false
 * 
 * @author dmle
 */
public class PCProg {
	private static Object YES = "Y";
	@DomainConstraint(mutable = true, optional = false)
	private final Set<PC> objs;

	/**
	 * @effects initialise this to have an empty set of PCs
	 */
	public PCProg() {
		objs = new Set<>();
	}

	/**
	 * @effects implement the factoryPC method of an instance of the singleton PCFactory
	 * 			to create some PCs and insert it into the objs attribute
	* */
	@DOpt(type = OptType.Other)
	public void createObjects(){
		PCFactory factory = PCFactory.getInstance();
		Scanner sc = new Scanner(System.in);
		while(YES.equals("Y")){
			getObjects().insert(factory.factoryPC());
			System.out.println("Do you want to create another pc ? [Y/N]");
			YES = sc.nextLine();
		}
	}

	/**
	 * @effects return the objs attribute
	* */
	@DOpt(type = OptType.Mutator)
	public Set<PC> getObjects() {
		return objs;
	}

	/**
	 * @effects if objs is not empty display to the standard console a text-based
	 *          tabular report on objs return this report else display nothing and
	 *          return null
	 */
	public String displayReport() {
		if (objs.size() > 0) {
			Vector<PC> pcs = objs.getElements();

			PCReport reportObj = new PCReport();
			return reportObj.displayReport(pcs.toArray(new PC[pcs.size()]));
		} else {
			return null;
		}
	}

	/**
	 * @effects save report to a file pcs.txt in the same directory as the program's
	 */
	public void saveReport(String report) {
		String fileName = "pcs.txt";
		writeFile(fileName);
		putln(report);
		writeStandardOutput();
	}

	/**
	 * The run method
	 * 
	 * @effects initialise an instance of PCProg create objects from data entered by
	 *          the user display a report on the objects prompt user to save report
	 *          to file if user answers "Y" save report else end
	 */
	public static void main(String[] args) {
		//
		PCProg prog = new PCProg();
		// create objects
		try {
			prog.createObjects();
			// display report
			String report = prog.displayReport();
			System.out.println(report);
			if (report != null) {
				// prompt user to save report
				putln("Save report to file? [Y/N]");
				String toSave = getln();
				if (toSave.equals("Y")) {
					prog.saveReport(report);
					putln("report saved");
				}
			}
		} catch (NotPossibleException e) {
			System.err.printf("%s: %s%n", e, e.getMessage());
		}
		putln("~END~");
	}

}
