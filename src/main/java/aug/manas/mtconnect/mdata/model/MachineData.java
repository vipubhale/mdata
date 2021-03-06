package aug.manas.mtconnect.mdata.model;

public class MachineData {


	private String machineId;
	private String machineName;
	private int partCount;
	private String activeAxes;
	
	private String s1Speed;
	private String s1Load;
	private String sSpeedOvr;
	private String s1Servo;

	private String servo;
	private String comms;
	private String logic;
	private String motion;
	private String system;
	
	public MachineData(){
		
	}
	/**
	 * 
	 * @param machineId
	 * @param machineName
	 * @param partCount
	 * @param activeAxes
	 * @param s1Speed
	 * @param s1Load
	 * @param sSpeedOvr
	 * @param s1Servo
	 * @param servo
	 * @param comms
	 * @param logic
	 * @param motion
	 * @param system
	 */
	
	public MachineData(String machineId, String machineName, int partCount, String activeAxes, String s1Speed,
			String s1Load, String sSpeedOvr, String s1Servo, String servo, String comms, String logic, String motion,
			String system) {
		super();
		this.machineId = machineId;
		this.machineName = machineName;
		this.partCount = partCount;
		this.activeAxes = activeAxes;
		this.s1Speed = s1Speed;
		this.s1Load = s1Load;
		this.sSpeedOvr = sSpeedOvr;
		this.s1Servo = s1Servo;
		this.servo = servo;
		this.comms = comms;
		this.logic = logic;
		this.motion = motion;
		this.system = system;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public int getPartCount() {
		return partCount;
	}

	public void setPartCount(int partCount) {
		this.partCount = partCount;
	}

	public String getActiveAxes() {
		return activeAxes;
	}

	public void setActiveAxes(String activeAxes) {
		this.activeAxes = activeAxes;
	}

	public String getS1Speed() {
		return s1Speed;
	}

	public void setS1Speed(String s1Speed) {
		this.s1Speed = s1Speed;
	}

	public String getS1Load() {
		return s1Load;
	}

	public void setS1Load(String s1Load) {
		this.s1Load = s1Load;
	}

	public String getsSpeedOvr() {
		return sSpeedOvr;
	}

	public void setsSpeedOvr(String sSpeedOvr) {
		this.sSpeedOvr = sSpeedOvr;
	}

	public String getS1Servo() {
		return s1Servo;
	}

	public void setS1Servo(String s1Servo) {
		this.s1Servo = s1Servo;
	}

	public String getServo() {
		return servo;
	}

	public void setServo(String servo) {
		this.servo = servo;
	}

	public String getComms() {
		return comms;
	}

	public void setComms(String comms) {
		this.comms = comms;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getMotion() {
		return motion;
	}

	public void setMotion(String motion) {
		this.motion = motion;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
	
	
	/**
	 * 
	 */
	
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "MachineData [machineId=" + machineId + ", machineName=" + machineName + ", partCount=" + partCount
				+ ", activeAxes=" + activeAxes + ", s1Speed=" + s1Speed + ", s1Load=" + s1Load + ", sSpeedOvr="
				+ sSpeedOvr + ", s1Servo=" + s1Servo + ", servo=" + servo + ", comms=" + comms + ", logic=" + logic
				+ ", motion=" + motion + ", system=" + system + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		MachineData machineData = null;
		if (obj != null && obj instanceof MachineData){
			machineData = (MachineData) obj;
		}
		if (
				machineData.getMachineName().equalsIgnoreCase(this.getMachineName()) 
				&& 
				machineData.getMachineId().equalsIgnoreCase(this.getMachineId()) 
				&&
				machineData.getPartCount() == this.getPartCount() 
				&&
				machineData.getActiveAxes().equalsIgnoreCase(this.getActiveAxes()) 
				&&
				machineData.getS1Speed().equalsIgnoreCase(this.getS1Speed())
				&&
				machineData.getS1Load().equalsIgnoreCase(this.getS1Load())
				&&
				machineData.getsSpeedOvr().equalsIgnoreCase(this.getsSpeedOvr())
				&&
				machineData.getServo().equalsIgnoreCase(this.getServo())
				&&
				machineData.getComms().equalsIgnoreCase(this.getComms())
				&&
				machineData.getLogic().equalsIgnoreCase(this.getLogic())
				&&
				machineData.getMotion().equalsIgnoreCase(this.getMotion())
				&&
				machineData.getSystem().equalsIgnoreCase(this.getSystem())
				){
			return true;
		}
		return false;
	}
}
