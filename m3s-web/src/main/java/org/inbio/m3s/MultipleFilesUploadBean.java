/**
 * 
 */
package org.inbio.m3s;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jgutierrez
 *
 */
public class MultipleFilesUploadBean {

	private MultipartFile file1;
	
	private MultipartFile file2;
	
	private MultipartFile file3;

	private MultipartFile file4;
	
	private MultipartFile file5;
	
	private MultipartFile file6;

	
	/**
	 * 
	 * @return
	 */
	public int getFilesCount(){
		
		int count = 0;
		
		if(file1 != null && !file1.isEmpty() )
			count++;
		if(file2 != null && !file2.isEmpty() )
			count++;
		if(file3 != null && !file3.isEmpty() )
			count++;
		if(file4 != null && !file4.isEmpty() )
			count++;
		if(file5 != null && !file5.isEmpty() )
			count++;
		if(file6 != null && !file6.isEmpty() )
			count++;
		
		return count;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<MultipartFile> getFiles(){
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		
		if(file1 != null && !file1.isEmpty() )
			list.add(file1);
		if(file2 != null && !file2.isEmpty() )
			list.add(file2);
		if(file3 != null && !file3.isEmpty() )
			list.add(file3);
		if(file4 != null && !file4.isEmpty() )
			list.add(file4);
		if(file5 != null && !file5.isEmpty() )
			list.add(file5);
		if(file6 != null && !file6.isEmpty() )
			list.add(file6);
		
		return list;
	}
	
	
	/**
	 * @return the file1
	 */
	public MultipartFile getFile1() {
		return file1;
	}

	/**
	 * @param file1 the file1 to set
	 */
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	/**
	 * @return the file2
	 */
	public MultipartFile getFile2() {
		return file2;
	}

	/**
	 * @param file2 the file2 to set
	 */
	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}

	/**
	 * @return the file3
	 */
	public MultipartFile getFile3() {
		return file3;
	}

	/**
	 * @param file3 the file3 to set
	 */
	public void setFile3(MultipartFile file3) {
		this.file3 = file3;
	}

	/**
	 * @return the file4
	 */
	public MultipartFile getFile4() {
		return file4;
	}

	/**
	 * @param file4 the file4 to set
	 */
	public void setFile4(MultipartFile file4) {
		this.file4 = file4;
	}

	/**
	 * @return the file5
	 */
	public MultipartFile getFile5() {
		return file5;
	}

	/**
	 * @param file5 the file5 to set
	 */
	public void setFile5(MultipartFile file5) {
		this.file5 = file5;
	}

	/**
	 * @return the file6
	 */
	public MultipartFile getFile6() {
		return file6;
	}

	/**
	 * @param file6 the file6 to set
	 */
	public void setFile6(MultipartFile file6) {
		this.file6 = file6;
	}
		
}
