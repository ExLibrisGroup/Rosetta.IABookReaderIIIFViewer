Rosetta.IIIFBookReaderViewer
================
Note: Version 5.30 is required.

To install the player, please follow these steps: 

Clone the viewer repository.

In the viewer package, open /web/conf/viewer.properties and replace the 'wsdlLocation' and 'deliveryServerUrl' properties hostname and port with your application server hostname and port. 

Repackage iiif-book-reader-viewer.war and deploy on your application server (it is recommended to use an external application server so that your viewer is not affected by future changes to the Rosetta application server).

Configure IIIF Book Reader as a Rosetta Viewer. The viewer should be configured as an IE viewer. No VPP is required.

Add IE delivery rule that uses the viewer. Make sure that the rule is placed below 'IIIF Manifest Viewer' delivery rule.

java version:1.8
