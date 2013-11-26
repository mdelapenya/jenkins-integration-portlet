# Jenkins Integration Portlet

## Motivation

More and more often, development teams adopt Continuous Integration as a good practice within development processes, and team members need to watch the results of its current work.

Jenkins is an open source continuous integration tool perfect for this goal, so many and many teams are using it as their preferred CI server.

As Jenkins provides many metrics by itself, I've decided to start a [Liferay](http://www.liferay.com) + [AlloyUI](http://www.alloyui.com) + [Jenkins](http://www.jenkins-ci.org) integration portlet that allows to display tests results in an easy and portal-deployable way. For that, a Liferay portal Admin, can deploy this portlet in his portal, allowing its users to watch last builds tests results, in an user-friendly style, using AlloyUI built-in cappabilities.

Below there are some screenshots of the current portlet:

<img title="Jenkins Integration Portlet" src="https://github.com/mdelapenya/jenkins-integration-portlet/raw/6.1.x/images/jenkins-integration-portlet-001.png" />
<img title="Jenkins Integration Portlet Configuration" src="https://github.com/mdelapenya/jenkins-integration-portlet/raw/6.1.x/images/jenkins-integration-portlet-002.png" />
<img title="Jenkins Integration Portlet Sucessfull Build" src="https://github.com/mdelapenya/jenkins-integration-portlet/raw/6.1.x/images/jenkins-integration-portlet-003.png" />
<img title="Jenkins Integration Portlet Failing-Tests Build" src="https://github.com/mdelapenya/jenkins-integration-portlet/raw/6.1.x/images/jenkins-integration-portlet-004.png" />
<img title="Jenkins Integration Portlet Broken Build" src="https://github.com/mdelapenya/jenkins-integration-portlet/raw/6.1.x/images/jenkins-integration-portlet-005.png" />

## Configuration

There is a properties files under 'docroot/WEB-INF/src/com/liferay/ci/http' named *connection.properties*. This file contains some properties with empty values for connecting to your Jenkins server, even if it is under a web server with Basic Authentication. You can extend that file with the *-ext* name convention, creating a *connection-ext.properties*, where you will put custom values for your application.

## v.1.1.0

* Supports two different display modes: default (as before), and a new one as a traffic light: red/green status depending of last build status 

## v.1.0.0

* Supports Basic Http Authentication
* Job name is configurable
* Maximum number of builds to display is configurable
* Chart types: dotted (default) and columns
* Supports stacked representation of data
* Supports displaying several build outputs: Passed tests count, Skipped tests count, Failed tests count and Total tests count


## License

This library, "Jenkins Integration Portlet", is free software ("Licensed Software"); you can redistribute it and/or modify it under the terms of the [GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl-2.1.html) as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; including but not limited to, the implied warranty of MERCHANTABILITY, NONINFRINGEMENT, or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

You should have received a copy of the [GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl-2.1.html) along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
