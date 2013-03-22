* ObjectDTO
	* creDate -> acronym
	* lastModificatin -> SpellingError
	* setLastModification -> Does nothing
	* hashCode & equals not on the same fields

* BaseDTO
	* constructor with ID -> does not call super
	
* Modif
	* French Class Name
	* field DTOID is in uppercase
	* Modif extends BaseDTO -> ObjectDTO where it's a delegate here...
	
* DateRange
	* dead code on two methods

* DateTimeUtils
	* compareDate is too big -> can be written in 3 lines
	* compareNotNull -> is using deprecated api
	* compareNotNull -> can be written in 1 line
	* right version of both methods are in DateRange mostly copied
	* isBusinessDay -> overly complex boolean expression
	* Spelling on variable DateDayOfWeak

* DayTypeScheme
	* Overly Complex Enum
	* variable naming c,v

* ARPSystemException
    * Extends runtime

* ExceptionSeverityScheme
    * has a default value that does means nothing



	