package msp.file.mapping;

import msp.utils.FileUtils;

public class MergeSplitMapping implements MappingMethod {

	@Override
	public int getBoxNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAuthentic(String[] target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean merge(String[] source, String target) {
		FileUtils.mergeFile(source, target);
		return true;
	}

	@Override
	public boolean split(String source, String[] target) {
		FileUtils.splitFile(source, target);
		return true;
	}
	

}
