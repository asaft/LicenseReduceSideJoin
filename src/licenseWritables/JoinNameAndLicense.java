package licenseWritables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class JoinNameAndLicense implements Writable {
	LicenseWritable _licenseWritable;
	public LicenseWritable get_licenseWritable() {
		return _licenseWritable;
	}
	String _writableType;
	@Override
	public void readFields(DataInput dataInput) throws IOException {
		// TODO Auto-generated method stub
		_writableType = WritableUtils.readString(dataInput);
		try {
			Class<?> cls = Class.forName(_writableType);
			System.out.println("Reading Writable Type:"+_writableType);
			_licenseWritable = (LicenseWritable )cls.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_licenseWritable.readFields(dataInput);
		
	}
	@Override
	public void write(DataOutput dataOutput) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Writing Writable Type:"+_writableType);
		WritableUtils.writeString(dataOutput, _writableType);
		_licenseWritable.write(dataOutput);
		
	}
	public void Set(LicenseWritable studentWritabel){
		_writableType = studentWritabel.getClass().getName();
		_licenseWritable = studentWritabel;
	}
	@Override
	public String toString() {
		return _writableType;
	}
	

}
