package Model;

/**Outsourced class. Used to create outsourced parts.*/
public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**Get company name.
     * @return String of the company name.*/
    public String getCompanyName() {
        return companyName;
    }

    /**Set company name. Sets the company name.
     * @param companyName to be set to.*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
