package licenseWritables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;



public class LicenseKey implements Writable,WritableComparable<LicenseKey>
{
	private String _id;
	private String _license_part; //0 - Student details, 1 - student grades
	@Override
	public int compareTo(LicenseKey licenseKey) {
		// TODO Auto-generated method stub
		int retVal = _id.compareTo(licenseKey.get_id());
		if (retVal == 0){
			retVal = _license_part.compareTo(licenseKey.get_license_part());
		}
		return retVal; 
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}

	@Override
	public void readFields(DataInput dataInput) throws IOException {
		// TODO Auto-generated method stub
		 _id = WritableUtils.readString(dataInput);
		 _license_part = WritableUtils.readString(dataInput);
		 
	}
	@Override
	public void write(DataOutput dataOutput) throws IOException {
		// TODO Auto-generated method stub
		WritableUtils.writeString(dataOutput,_id);
		WritableUtils.writeString(dataOutput,_license_part);
		
	}
	@Override
	public String toString(){
		return _id+","+_license_part;
	}
	public String get_license_part() {
		return _license_part;
	}
	public void set_license_part(String _license_part) {
		this._license_part = _license_part;
	}
}
	