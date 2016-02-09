package strategies;

import licenseWritables.LicenseNameWritable;
import infra.WritableStrategy;

public class LicenseNameWritableParsingStrategy implements ILicenseNameParsingStrategy {
	LicenseNameWritable _personName;

	public LicenseNameWritableParsingStrategy(){
		 
	}
	@Override
	public void Parse(String line) {
		// TODO Auto-generated method stub
		String []v = line.split(",");
		_personName.set_name(v[1]); 
	}

	@Override
	public void set_WritableStrategy(WritableStrategy writableStrategy) {
		// TODO Auto-generated method stub
		_personName= (LicenseNameWritable)writableStrategy;
	}

}
