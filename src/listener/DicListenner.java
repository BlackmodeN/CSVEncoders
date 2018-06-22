package listener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public final class DicListenner {
	private static FileAlterationMonitor monitor;

	private static final String My_DIR = "C:\\Users\\DELL-5490\\Documents\\problem";

	public static void start() {
		registerListenner();
		try {
			monitor.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			monitor.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void registerListenner() {
		System.out.println("File Monitor create...");

		// File Monitoring

		// Create a new observer for the folder and add a listener
		// that will handle the events in a specific directory and take action.
		File parentDir = FileUtils.getFile(My_DIR);

		FileAlterationObserver observer = new FileAlterationObserver(parentDir);
		observer.addListener(new FileAlterationListenerAdaptor() {

			@Override
			public void onFileCreate(File file) {
				try {
					Transfer.TransCode(file);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFileDelete(File file) {
				System.out.println("File deleted: " + file.getName());
			}

			@Override
			public void onDirectoryCreate(File dir) {
				System.out.println("Directory created: " + dir.getName());
			}

			@Override
			public void onDirectoryDelete(File dir) {
				System.out.println("Directory deleted: " + dir.getName());
			}
		});

		// Add a monior that will check for events every x ms,
		// and attach all the different observers that we want.
		monitor = new FileAlterationMonitor(500, observer);

	}
}