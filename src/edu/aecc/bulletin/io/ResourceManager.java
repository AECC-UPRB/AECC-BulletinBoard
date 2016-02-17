package edu.aecc.bulletin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that manages the resource loading threads with the intention of loading
 * a set of files.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public final class ResourceManager<E> {

	private ArrayList<ResourceLoadingThread<E>> thread;
	private File directory;
	private ResourceLoader<E> loader;

	/**
	 * Initializes the instance.
	 * 
	 * @param dir
	 *            Directory to be loaded
	 * @param l
	 *            Resource loader to be used for file processing
	 */
	public ResourceManager(File dir, ResourceLoader<E> l) {
		if (dir == null)
			throw new RuntimeException("Given file is null");
		if (!dir.isDirectory())
			throw new RuntimeException("Given file is not a directory");
		directory = dir;
		thread = new ArrayList<ResourceLoadingThread<E>>();
		loader = l;
	}

	/**
	 * Processes all of the files in the target directory and returns an
	 * ArrayList containing the processed data.
	 * 
	 * @return ArrayList containing processed data
	 */
	public ArrayList<E> loadResources() {
		System.out.println(String.format("Commencing the loading of resources in '%s'", directory.getName()));
		Stack<File> fStack = new Stack<File>();
		File[] fList = directory.listFiles();
		System.out.println(String.format("%d items have been detected in '%s'", fList.length, directory.getName()));
		for (File f : fList)
			fStack.push(f);
		ArrayList<E> dataList = new ArrayList<E>();
		int x;
		int cpus = Runtime.getRuntime().availableProcessors();
		System.out.println(String.format("Loading resources with %d CPUs", cpus));
		for (x = 0; x < cpus; x++)
			thread.add(new ResourceLoadingThread<E>(loader, fStack, dataList));
		for (ResourceLoadingThread<E> l : thread)
			l.start();
		int alive = 1;
		while (alive > 0) {
			alive = thread.size();
			for (x = 0; x < thread.size(); x++)
				if (!thread.get(x).isAlive())
					alive--;
		}
		System.out.println(String.format("%d items have been loaded", dataList.size()));
		return dataList;
	}
}
