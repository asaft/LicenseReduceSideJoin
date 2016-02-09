package strategies;


import licenseWritables.LicenseTypeWritable;
import infra.WritableStrategy;

public class LicenseTypeWritableParsingStrategy implements ILicenseNameParsingStrategy {
	LicenseTypeWritable _license;

	public LicenseTypeWritableParsingStrategy(){
		 
	}
	@Override
	public void Parse(String line) {
		// TODO Auto-generated method stub
		String []v = line.split(",");
		_license.set_type(v[1]); 
	}

	@Override
	public void set_WritableStrategy(WritableStrategy writableStrategy) {
		// TODO Auto-generated method stub
		_license= (LicenseTypeWritable)writableStrategy;
	}

}
