//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.28 at 10:32:49 PM EDT 
//


package aug.manas.mtconnect.mtdata.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         DEPRECATED: An Notifcation event
 *       
 * 
 * <p>Java class for AlarmType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AlarmType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;urn:mtconnect.org:MTConnectStreams:1.3>EventType">
 *       &lt;attribute name="code" use="required" type="{urn:mtconnect.org:MTConnectStreams:1.3}NotifcationCodeType" />
 *       &lt;attribute name="severity" type="{urn:mtconnect.org:MTConnectStreams:1.3}SeverityType" />
 *       &lt;attribute name="state" type="{urn:mtconnect.org:MTConnectStreams:1.3}AlarmStateType" />
 *       &lt;attribute name="nativeCode" use="required" type="{urn:mtconnect.org:MTConnectStreams:1.3}NativeNotifcationCodeType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlarmType")
public class AlarmType
    extends EventType
{

    @XmlAttribute(name = "code", required = true)
    protected NotifcationCodeType code;
    @XmlAttribute(name = "severity")
    protected SeverityType severity;
    @XmlAttribute(name = "state")
    protected AlarmStateType state;
    @XmlAttribute(name = "nativeCode", required = true)
    protected String nativeCode;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link NotifcationCodeType }
     *     
     */
    public NotifcationCodeType getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotifcationCodeType }
     *     
     */
    public void setCode(NotifcationCodeType value) {
        this.code = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link SeverityType }
     *     
     */
    public SeverityType getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeverityType }
     *     
     */
    public void setSeverity(SeverityType value) {
        this.severity = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link AlarmStateType }
     *     
     */
    public AlarmStateType getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlarmStateType }
     *     
     */
    public void setState(AlarmStateType value) {
        this.state = value;
    }

    /**
     * Gets the value of the nativeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNativeCode() {
        return nativeCode;
    }

    /**
     * Sets the value of the nativeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNativeCode(String value) {
        this.nativeCode = value;
    }

}
