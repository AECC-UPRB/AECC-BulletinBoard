package edu.aecc.bulletin.io;

import java.io.File;

/**
 * Interface that handles the loading of resources.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public interface ResourceLoader<E> {

	/**
	 * Processes the given file and returns the reuslt.
	 * 
	 * @param f
	 *            Target file
	 * @return Processed data from target file
	 */
	public abstract E loadResource(File f);
}