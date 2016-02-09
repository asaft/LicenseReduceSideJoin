package license;
import licenseWritables.JoinNameAndLicense;
import licenseWritables.LicenseKey;
import licenseWritables.LicenseKeyPartitioner;
import infra.IParsingStrategy;
import inputFormats.LicensesWritableInputFormat;
import inputFormats.NamesWritableInputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import strategies.ILicenseNameParsingStrategy;
import strategies.ILicenseTypeParsingStrategy;
import strategies.LicenseNameWritableParsingStrategy;
import strategies.LicenseTypeWritableParsingStrategy;



public class LicenseDriver {

	public static void main(String[] args) throws Exception {  
		if (args.length != 3) {           
			System.out.println("usage: [students dataset path] [grades dataset path] [output]");           
			System.exit(-1);         
		}
		Configuration configuration = new Configuration();
		configuration.setClass(ILicenseNameParsingStrategy.class.getName(),
				LicenseNameWritableParsingStrategy.class,
				IParsingStrategy.class);
		configuration.setClass(ILicenseTypeParsingStrategy.class.getName(),
				LicenseTypeWritableParsingStrategy.class,
				IParsingStrategy.class);

		
		Job job = Job.getInstance(configuration);         
		job.setOutputKeyClass(LicenseKey.class);
		job.setOutputValueClass(JoinNameAndLicense.class);
		MultipleInputs.addInputPath(job, new Path(args[0]), NamesWritableInputFormat.class,NamesDetailsMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]), LicensesWritableInputFormat.class,LicensesDetailsMapper.class);
		job.setReducerClass(LicenseReducer.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setPartitionerClass(LicenseKeyPartitioner.class);
		job.setGroupingComparatorClass(LicenseGroupingComparator.class);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		job.setJarByClass(LicenseDriver.class);          
		job.submit();                                          
	} 
}