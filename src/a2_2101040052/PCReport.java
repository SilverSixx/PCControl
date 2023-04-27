package a2_2101040052;

import utils.DOpt;
import utils.OptType;

/**
 * @overview PCReport is a program that implements only one function producing the pc program report
* */
public class PCReport {

    /**
     * the displayReport method
     * @effects return a standardized report which contains all the input computers by user
    * */
    @DOpt(type= OptType.Other)
    public String displayReport(PC[] objs) {
        StringBuilder sb = new StringBuilder();
        String line = "---------------------------------------------------------------------------------------------------" ;
        String pcreport = String.format("%43s%s%43s\n", " ","PCPROG REPORT", " ");
        sb.append(line).append("\n");
        sb.append(pcreport);
        sb.append(line).append("\n");
        for(int i = 1; i <= objs.length; i++) {
            PC p = objs[i-1];
            String pcStr = String.format("%3d %20s %6d %15s %-51s", i ,p.getModel(), p.getYear(), p.getManufacturer(), p.compsToString());
            sb.append(pcStr).append("\n");
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
