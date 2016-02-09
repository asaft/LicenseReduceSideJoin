package license;

import java.io.IOException;

import licenseWritables.JoinNameAndLicense;
import licenseWritables.LicenseKey;
import licenseWritables.LicenseNameWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NamesDetailsMapper  extends Mapper<Text, LicenseNameWritable, LicenseKey, JoinNameAndLicense> 
{
	
	private JoinNameAndLicense outValue = new JoinNameAndLicense();
	private LicenseKey keyOut = new LicenseKey(); 
	@Override 
	public void map(Text key, LicenseNameWritable value,    Context context) throws IOException ,InterruptedException 
	{
		keyOut.set_id(key.toString());
		keyOut.set_license_part("0");
		outValue.Set(value);
		context.write(keyOut, outValue);
	}
}
 
