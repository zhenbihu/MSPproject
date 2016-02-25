/**
 * Author: Shixin Tian
 * Date: 2015.12.22
 * 
 * Interface for mapping
 * Two methods are needed: one for merging and the other for splitting
 */
package msp.file.mapping;

import java.io.File;

public interface MappingMethod {
	public int getBoxNum();
	public boolean isAuthentic(String[] target);
	public boolean merge(String[] source,String target);
	public boolean split(String source, String[] target);
}
