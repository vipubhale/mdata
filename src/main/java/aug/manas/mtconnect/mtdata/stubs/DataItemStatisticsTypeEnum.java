//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.28 at 10:32:49 PM EDT 
//


package aug.manas.mtconnect.mtdata.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataItemStatisticsTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DataItemStatisticsTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MINIMUM"/>
 *     &lt;enumeration value="MAXIMUM"/>
 *     &lt;enumeration value="AVERAGE"/>
 *     &lt;enumeration value="STANDARD_DEVIATION"/>
 *     &lt;enumeration value="ROOT_MEAN_SQUARE"/>
 *     &lt;enumeration value="MEAN"/>
 *     &lt;enumeration value="MODE"/>
 *     &lt;enumeration value="RANGE"/>
 *     &lt;enumeration value="KURTOSIS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DataItemStatisticsTypeEnum")
@XmlEnum
public enum DataItemStatisticsTypeEnum {

    MINIMUM,
    MAXIMUM,
    AVERAGE,
    STANDARD_DEVIATION,
    ROOT_MEAN_SQUARE,
    MEAN,
    MODE,
    RANGE,
    KURTOSIS;

    public String value() {
        return name();
    }

    public static DataItemStatisticsTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
