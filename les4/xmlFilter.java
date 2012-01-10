package les4;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class xmlFilter extends FileFilter
{
	public String getDescription()
	{
		return "XML Files (*.xml)";
	}

	public boolean accept(File file)
	{
		if (file.isDirectory())
		{
			return true;
		}
		else
		{
			String filepathname = file.getAbsolutePath().toLowerCase();
			if (filepathname.endsWith(".xml"))
			{
				return true;
			}
		}
		return false;
	}
}
