package license;

import java.io.IOException;

import licenseWritables.JoinNameAndLicense;
import licenseWritables.LicenseKey;
import licenseWritables.LicenseTypeWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class LicensesDetailsMapper  extends Mapper<Text, LicenseTypeWritable, LicenseKey, JoinNameAndLicense> 
{
	
	private JoinNameAndLicense outValue = new JoinNameAndLicense();
	private LicenseKey keyOut = new LicenseKey(); 
	@Override 
	public void map(Text key, LicenseTypeWritable value,    Context context) throws IOException ,InterruptedException 
	{
		keyOut.set_id(key.toString());
		keyOut.set_license_part("1");
		outValue.Set(value);
		context.write(keyOut, outValue);
	}
}

 
