package inputFormats;
import infra.IParsingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import licenseWritables.LicenseNameWritable;
import licenseWritables.LicenseWritable;




import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import strategies.ILicenseNameParsingStrategy;




public class NamesWritableInputFormat extends FileInputFormat<Text,LicenseWritable> {

	@Override
	public RecordReader<Text,LicenseWritable > createRecordReader(InputSplit split,
			TaskAttemptContext context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		RecordReader<Text, LicenseWritable> retVal = new LicenseWritableRecordReader();
		
		return retVal;
	}
	
	
	protected class LicenseWritableRecordReader extends RecordReader<Text, LicenseWritable>{
		Configuration _configuration; 
		private Path _file = null;
		private FSDataInputStream  _in; 
		Text _currentKey = new Text(); 
		protected LicenseWritable _currentValue = null;
		long _start,_end,_pos; 
		BufferedReader _reader = null;
		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			_in.close();
		}

		@Override
		public Text getCurrentKey() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return _currentKey;
		}

		@Override
		public LicenseWritable getCurrentValue() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return _currentValue;
		}

		@Override
		public float getProgress() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			if (_start == _end){
				return 0.0f;
			}
			
			return Math.min(1.0f, (_pos - _start) / (float)(_end - _start));

		}

		@Override
		public void initialize(InputSplit split, TaskAttemptContext context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			FileSplit filesplit = (FileSplit) split;
	        _file = filesplit.getPath();
	        _configuration= context.getConfiguration();
	        FileSystem fs = _file.getFileSystem(_configuration);
	        _currentValue = new LicenseNameWritable();
	        _currentValue.set_parsingStrategy(getParsingStrategy(ILicenseNameParsingStrategy.class));
	        _in = fs.open(_file);
	        _start = filesplit.getStart();
	        _end = _start+filesplit.getLength();
	        _pos = _start;
	        _reader = new BufferedReader(new InputStreamReader(_in));
	        System.out.println("Input Format Initialize:"+_file.getName());
	        
	        
	        
	        
			
		}
		
		protected IParsingStrategy getParsingStrategy(Class<? extends IParsingStrategy> cls){
			IParsingStrategy retVal = null; 
			List<IParsingStrategy> l = _configuration.getInstances(
					cls.getName(), 
					IParsingStrategy.class);
			
			retVal = l.get(0);
			return retVal; 
		}

		@Override
		public boolean nextKeyValue() throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			
			
			String line = _reader.readLine();
			
			
			boolean retVal = false; 
			if (line != null){
				retVal = true;
				//Here we will split the line and get the key value pairs
				String []kv = line.split(",");
				_currentKey.set(kv[0]);
				IParsingStrategy strategy = _currentValue.get_parsingStrategy();
				strategy.set_WritableStrategy(_currentValue);
				
				strategy.Parse(line);
				_pos = _in.getPos();
				System.out.println("File pos"+_pos);
			
			}
			
			return retVal;
		}

			
	}

	

}
