package infra;

public interface IParsingStrategy {
	void Parse(String line); 
	void set_WritableStrategy(WritableStrategy writableStrategy);
}
