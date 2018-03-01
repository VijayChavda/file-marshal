# file-marshal
One solution for organizing and removing duplicate files.

## Sources
Specifies a list of sources (directories) from where fils to operate should be taken.

## File selection
Specifies how files are selected from the sources. Only selected files are operated.  
Files can be selected based on their:
 * Type / Extension
 * Size

## Main features:
#### Declone  
Identifies and removes duplicate files in an intelligent way.  
Files can be determined as duplicates based on following factors and their settings:
 1. **Name**  
	* _Similar_: The file names should be similar.  
	* _Common words_: Looks for common words in file names.
	* _Similar common words_: Looks for common words that are similar.
	* _Exactly same_: File names should be exactly same.
 2. **Size**  
	* _No huge difference_: Files can be of different size, but not too different.  
	* _Almost same_: File can be of almost same size.
	* _Exactly same_: Files can be of the same size.
 3. **Content**  
	* File's content can be matched. You can also specify how much content of the file will be matched. (2%, 10%, 20%, 50%, 100%)
	* This setting affect the time it takes to determine if files are duplicate.
 
 #### Declutter  
Organizes your files based on following settings:  
 1. **Output location**  
	* Files will be organized in a folder in this location.
 2. **Mode** - Determines how the operation is carried out.  
	* _Move_: Files are moved to a new location.
	* _Copy_: Original files are kept as they are, and a copy of them are organizd.
	* _Simulation_: Not actual files, but shortcuts to the files are organized.
 3. **Organize by** - Determines how files are organized.  
	* _Type_: Files are organized based on their type. (Example: Image, Video, Document, etc.)
	* _Extension_: Files are organized based on their extension. (Example: JPG, AVI, PDF, etc.)
	* _Both_: Files are organized by their type, then by their extension. (Example: Image/JPG, Image/PNG, Music/MP3, etc.)
	* _I'll decide_: Files are organized in a custom way, based on extensions specified by user.
 4. **More settings** - Settings that determine:
	* How files with unidentified type/extension are grouped.
	* How files without any extensions are grouped.
	* How files too less to group should be handled.
	
#### Big Bullie Hunter
Finds out top 10 largest files from the file sources.
