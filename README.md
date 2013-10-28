# Jenkins Integration Portlet

## Motivation

More and more often, development teams adopt Continuous Integration as a good practice within development processes, and team members need to watch the results of its current work.

Jenkins is an open source continuous integration tool perfect for this goal, so many and many teams are using it as their preferred CI server.

As Jenkins provides many metrics by itself, I've decided to start a [Liferay](http://www.liferay.com) + [AlloyUI](http://www.alloyui.com) + [Jenkins](http://www.jenkins-ci.org) integration portlet that allows to display tests results in an easy and portal-deployable way. For that, a Liferay portal Admin, can deploy this portlet in his portal, allowing its users to watch last builds tests results, in an user-friendly style, using AlloyUI built-in cappabilities.

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