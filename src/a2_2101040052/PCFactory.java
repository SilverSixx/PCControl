package a2_2101040052;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @overview PCFactory is a singleton program which implements 1 function to create PC
 * @attributes
 *      instance    PCFactory
 *      sc          Scanner
 * @object a typical PCFactory is only accessed after calling the getInstance method
 *         because it is a singleton class
* */
public final class PCFactory {
    @DomainConstraint(type = "PCFactory")
    private static PCFactory instance;
    @DomainConstraint(type = "Scanner")
    private final Scanner sc;

    /**
     * @effects initialise new Scanner object and assign it to the sc attribute
    * */
    private PCFactory() {
        sc = new Scanner(System.in);
    }

    /**
     * This is the only way to access to the instance of PCFactory because of its being as a singleton
     *
     * @effects return the instance of the PCFactory class
     * */
    @DOpt(type = OptType.Other) @AttrRef("instance")
    public static PCFactory getInstance() {
        if (instance == null) {
            instance = new PCFactory();
        }
        return instance;
    }

    /**
     * The factoryPC method
     *
     * @effects return a PC object which has some fields inputed by users
     *          if and only if they fill in the appropriate type of inputs as same as the defined in method
     *          otherwise it throws an exception to the console
     * */
    @DOpt(type = OptType.Other)
    public PC factoryPC() {
        try{
            System.out.println("Please input some fields to form your pc!");
            System.out.print("Enter the model: ");
            String model = sc.nextLine();
            System.out.print("Enter the year published: ");
            Integer year = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter the manufacturer: ");
            String manufacturer = sc.nextLine();
            Set<String> pcComps = new Set<>();
            while (true) {
                System.out.println("Enter the components: ");
                String comp = sc.nextLine();
                if (comp.equals("")) {
                    break;
                }
                pcComps.insert(comp);
            }
            return new PC(model, year, manufacturer, pcComps);
        } catch (InputMismatchException e){
            throw new InputMismatchException("Your input year is not validated in our system");
        }
    }
}
