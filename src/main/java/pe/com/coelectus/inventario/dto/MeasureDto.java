package pe.com.coelectus.inventario.dto;

public class MeasureDto {

	private Integer measureId;
	private String name;
	private String sign;
	
	public Integer getMeasureId() {
		return measureId;
	}
	public void setMeasureId(Integer measureId) {
		this.measureId = measureId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@Override
	public String toString() {
		return "MeasureDto [measureId=" + measureId + ", name=" + name + ", sign=" + sign + "]";
	}
}
