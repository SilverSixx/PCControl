package a2_2101040052;

import utils.*;

import java.util.Objects;

/**
 * @overview PC is a device that has the following attributes or components as listed below
 * @attributes
 *      model        String
 *      year         Integer
 *      manufacturer String
 *      comps        Set<String>
 * @object a typical PC object is p=<mo,y,ma,{c1,c2, ... cn}>, where model(mo), year(y), manufacturer(ma), c1 c2 ... cn are components
 * @abstract_propeties
 *      mutable(model) = true /\ optional(model) = false /\ length(model) = 20
 *      mutable(year) = false /\ optional(year) = false /\ min(year) = 1984
 *      mutable(manufacturer) = false /\ optional(manufacturer) = false /\ length(manufacturer) = 15
 *      mutable(comps) = true /\ optional(comps) = false /\ comps != {} -> (for all x in comps. x is a string)
* */
public class PC {
    @DomainConstraint(type = "String",mutable = true, optional = false, length = 20)
    private String model;
    @DomainConstraint(type = "Integer",mutable = false,optional = false,min = 1984)
    private Integer year;
    @DomainConstraint(type = "String",mutable = false,optional = false,length = 15)
    private String manufacturer;
    @DomainConstraint(type = "Set<String>",mutable = true,optional = false)
    private Set<String> comps;

    /**
     * @effects
     * if model is validated /\ year is validated /\ manufacturer is validated
     *      initialise a PC object as <model, year, manufacturer, comps>
     * else
     *      throw NotPossibleException
     */
    public PC(@AttrRef("model") String model,@AttrRef("year") Integer year,@AttrRef("manufacturer") String manufacturer,@AttrRef("comps") Set<String> comps) throws NotPossibleException {
        if(!validateModel(model)){
            throw new NotPossibleException("Model not valid");
        }
        if(!validateYear(year)){
            throw new NotPossibleException("Year not valid");
        }
        if(!validateManufacturer(manufacturer)){
            throw new NotPossibleException("Manufacturer not valid");
        }
        this.model = model;
        this.year = year;
        this.manufacturer = manufacturer;
        this.comps = comps;
    }

    /**
     * @effects return this.model
     */
    @DOpt(type= OptType.Observer) @AttrRef("model")
    public String getModel() {
        return model;
    }

    /**
     * @effects set this.model = model
    * */
    @DOpt(type= OptType.Mutator) @AttrRef("model")
    public void setModel(String model) {
        if(!validateModel(model)){
            throw new NotPossibleException("Model not valid");
        }
        this.model = model;
    }

    /**
     * @effects return year attribute
     */
    @DOpt(type= OptType.Observer) @AttrRef("year")
    public Integer getYear() { return year; }

    /**
     * @effects set this.year = year
     */
    @DOpt(type=OptType.Mutator) @AttrRef("year")
    public void setYear(Integer year) {
        if(!validateYear(year)){
            throw new NotPossibleException("Year not valid");
        }
        this.year = year;
    }

    /**
     * @effects return manufacturer attribute
     */
    @DOpt(type= OptType.Observer) @AttrRef("manufacturer")
    public String getManufacturer() { return manufacturer; }

    /**
     * @effects set this.manufacturer = manufacturer
    **/
    @DOpt(type= OptType.Mutator) @AttrRef("manufacturer")
    public void setManufacturer(String manufacturer) {
        if(!validateManufacturer(manufacturer)){
            throw new NotPossibleException("Manufacturer not valid");
        }
        this.manufacturer = manufacturer;
    }

    /**
     * @effects return comps attribute
     */
    @DOpt(type= OptType.Observer) @AttrRef("comps")
    public Set<String> getComps() {return comps;}

    /**
     * @effects set this.comps = comps
     * */
    @DOpt(type=OptType.Mutator) @AttrRef("comps")
    public void setComps(Set<String> comps) {
        this.comps = comps;
    }

    /**
     *
     * @effects
     *  if model.length() <= 20
     *      return true
     *  else
     *      return false
     */
    public boolean validateModel(String model) {
        return model.length() <= 20;
    }

    /**
     *
     * @effects
     *  if manufacturer.length() <= 15
     *      return true
     *  else
     *      return false
     */
    public boolean validateManufacturer(String manufacturer) {
        return manufacturer.length() <= 15;
    }

    /**
     *
     * @effects
     *  if year >= 1984
     *      return true
     *  else
     *      return false
     */
    public boolean validateYear(Integer year) {
        return year >= 1984;
    }

    /**
     * Compares this PC object with the specified object (o) for equality.
     * Returns true if and only if the specified object is also a PC object and all its fields are equal to the fields of this PC object.
     *
     * @effects return true if the specified object is equal to this PC object, otherwise return false
     */
    @DOpt(type=OptType.Default)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PC pc)) return false;
        return    Objects.equals(getModel(), pc.getModel())
               && Objects.equals(getYear(), pc.getYear())
               && Objects.equals(getManufacturer(), pc.getManufacturer())
               && Objects.equals(getComps(), pc.getComps());
    }

    /**
     * @effects return a formatted string of a PC object
    * */
    @DOpt(type=OptType.Default)
    @Override
    public String toString() {
        return String.format("PC<%s,%d,%s,%s>", this.model, this.year, this.manufacturer, this.comps.toString());
    }

    /**
     * @effects return a formatted string of the comps of a PC object
    * */
    @DOpt(type=OptType.Other)
    public String compsToString(){
        return comps.toString().substring(3).replace('{','[').replace('}',']');
    }

    /**
     *
     * @effects return the representation of all validation
     */
    @DOpt(type=OptType.Helper)
    public boolean repOk(){
        return validateManufacturer(manufacturer) && validateModel(model) && validateYear(year);
    }

}
