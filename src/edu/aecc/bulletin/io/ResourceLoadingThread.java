package edu.aecc.bulletin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Thread in charge of processing files.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public final class ResourceLoadingThread<E> implements Runnable {

	private ResourceLoader<E> loader;
	private Stack<File> fileStack;
	private ArrayList<E> resultList;
	private Thread thread;

	/**
	 * Initializes the instance.
	 * 
	 * @param l
	 *            Resource loader to be used for file processing
	 * @param fStack
	 *            Stack of files to be processed
	 * @param rList
	 *            ArrayList to put the processed data in
	 */
	public ResourceLoadingThread(ResourceLoader<E> l, Stack<File> fStack, ArrayList<E> rList) {
		loader = l;
		fileStack = fStack;
		resultList = rList;
	}

	/**
	 * Starts the file processing task.
	 */
	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Returns whether or not the thread is alive.
	 * 
	 * @return Whether or not the thread is alive
	 */
	public boolean isAlive() {
		return thread.isAlive();
	}

	@Override
	public synchronized void run() {
		while (!fileStack.isEmpty())
			resultList.add(loader.loadResource(fileStack.pop()));
	}
}