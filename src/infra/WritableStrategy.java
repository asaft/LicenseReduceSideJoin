package infra;

public class WritableStrategy  {
	IParsingStrategy _parsingStrategy;
	IMappingStrategy _mappingStrategy;
	IReducingStrategy _reducingStrategy;
	public IParsingStrategy get_parsingStrategy() {
		return _parsingStrategy;
	}
	public void set_parsingStrategy(IParsingStrategy _parsingStrategy) {
		this._parsingStrategy = _parsingStrategy;
		_parsingStrategy.set_WritableStrategy(this);
	}
	public IMappingStrategy get_mappingStrategy() {
		return _mappingStrategy;
	}
	public void set_mappingStrategy(IMappingStrategy  _mappingStrategy) {
		this._mappingStrategy = _mappingStrategy;
		_mappingStrategy.set_WritableStrategy(this);
		
	}
	public IReducingStrategy get_reducingStrategy() {
		return _reducingStrategy;
	}
	public void set_reducingStrategy(IReducingStrategy _reducingStrategy) {
		this._reducingStrategy = _reducingStrategy;
	}

	
	
	

}
