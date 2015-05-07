/*******************************************************************************
 * Copyright (c) 2014 Matthieu Helleboid.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Matthieu Helleboid - initial API and implementation
 ******************************************************************************/
package org.piwigo.remotesync.ui.pivot;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;
import org.piwigo.remotesync.api.conf.ConfigUtil;

public class RemotesyncUI extends Window implements Application {

	private GalleryConfigForm galleryConfigForm;

	public void startup(final Display display, final Map<String, String> properties) throws Exception {
		galleryConfigForm = (GalleryConfigForm) new BXMLSerializer().readObject(GalleryConfigForm.class, "galleryConfigForm.bxml");
		galleryConfigForm.loadAccessors();

		this.setContent(galleryConfigForm);
		this.setTitle("Piwigo Remote Sync");
		this.setMaximized(true);
		this.open(display);
	}

	public boolean shutdown(final boolean optional) throws Exception {
		galleryConfigForm.saveAccessors();
		ConfigUtil.INSTANCE.saveUserConfig();
		
		this.close();
		return false;
	}

	public void suspend() throws Exception {
	}

	public void resume() throws Exception {
	}

	public static void run() {
		DesktopApplicationContext.main(RemotesyncUI.class, new String[0]);
	}
}