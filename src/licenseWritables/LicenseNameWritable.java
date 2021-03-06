package licenseWritables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableUtils;

public class LicenseNameWritable extends LicenseWritable {
  private String _id;
  private String _name;
	public String get_id() {
	return _id;
}

public void set_id(String _id) {
	this._id = _id;
}

public String get_name() {
	return _name;
}

public void set_name(String _name) {
	this._name = _name;
}

	@Override
	public void readFields(DataInput dataInput) throws IOException {
		_id = WritableUtils.readString(dataInput);
		_name = WritableUtils.readString(dataInput);

	}

	@Override
	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeString(dataOutput,_id);
		WritableUtils.writeString(dataOutput,_name);
	}

}
