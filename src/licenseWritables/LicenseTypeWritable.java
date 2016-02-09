package licenseWritables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableUtils;

public class LicenseTypeWritable extends LicenseWritable {
 private String _id;
 private String _type;
	public String get_id() {
	return _id;
}

public void set_id(String _id) {
	this._id = _id;
}

public String get_type() {
	return _type;
}

public void set_type(String _type) {
	this._type = _type;
}

	@Override
	public void readFields(DataInput dataInput) throws IOException {
		_id = WritableUtils.readString(dataInput);
		_type = WritableUtils.readString(dataInput);

	}

	@Override
	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeString(dataOutput,_id);
		WritableUtils.writeString(dataOutput,_type);

	}

}
