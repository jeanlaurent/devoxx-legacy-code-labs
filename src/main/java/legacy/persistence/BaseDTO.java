package legacy.persistence;

/**
 * <p>
 *     Title: legacy.BaseDTO
 * </p>
 * <P>
 *     Description: basic object
 * </P>
 * @author bPoolvoorde
 * @version 1.0beta
 * @creationDate June 17, 2005
 */
public class BaseDTO extends ObjectDTO {

	/**
	 * "null"
	 */
	public static final int NO_ID = 0;

	/**
	 * This object's id
	 */
	@PrimaryKey
	private int id = BaseDTO.NO_ID;

	/**
	 * default constructor legacy.persistence.BaseDTO Constructor
	 */
	public BaseDTO() {
		super();
	}

	/**
	 * legacy.persistence.BaseDTO
	 *
	 * @param id
	 */
	public BaseDTO(int id) {
		this.id = id;
	}

	/**
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * 			the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return "BaseDTO{" +
                "id=" + id +
                "} " + super.toString();
    }
}
