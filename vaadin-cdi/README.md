vaadin-cdi
==========

Vaadin-CDI is the official CDI integration for Vaadin framework version 7.


Changes in 1.0.0.beta4
-----------------------

- removed the blocking of injecting Vaadin components in normal scopes
  that was introduced in 1.0.0.beta3

While it is now possible to inject Vaadin components in normal scopes
(including @NormalUIScoped and @NormalViewScoped), it is not recommended
as the proxies generated by CDI implementations may cause complications e.g.
with Java collections. Please report any such issues in the Vaadin framework
as tickets at http://dev.vaadin.com/newticket .

For components injected in normal scopes, interceptors and decorators should
only be used for methods that are not called directly by the framework.  

See also the notes for earlier releases below.

Changes in 1.0.0.beta3
-----------------------

- breaking change: @UIScoped and @ViewScoped are no longer @NormalScope
  - added corresponding @NormalUIScoped and @NormalViewScoped
- breaking change: NormalScopes (including @NormalUIScoped and
  @NormalViewScoped) are not supported classes implementing
  com.vaadin.ui.Component
- See http://dev.vaadin.com/query?status=closed&status=released&milestone=Vaadin+CDI+1.0.0.beta3
  for the complete list of changes.

See also the notes for earlier releases below.

Changes in 1.0.0.beta2
-----------------------

- @UIScoped and @ViewScoped can be used on methods
  (producer methods, setter injection, constructor injection)
- Dependency on Guava eliminated
- Various fixes including
  - UI and View are now correctly included in their own scopes
  - Sub-views with slash in view name are now handled correctly
- See http://dev.vaadin.com/query?status=closed&status=released&milestone=Vaadin+CDI+1.0.0.beta2
  for the complete list of changes.

See also the notes for earlier releases below.

Changes in 1.0.0.beta1
-----------------------

Note: Vaadin CDI 1.0.0.beta1 and later requires Vaadin 7.3.1 or later.
Injection of beans other than components also works with earlier Vaadin
versions.

See also the changes in 1.0.0.alpha3 below for a breaking change (view and UI
name mappings) when upgrading from earlier versions.

- @ViewScoped context
- @UIScoped and @ViewScoped use @NormalScope, supporting interceptors etc.
- Better support for server push (including WebSockets) and operations in
  background threads (within UI.access())
- DeltaSpike library is used internally by the add-on
- Various fixes and small enhancements
- See http://dev.vaadin.com/query?status=closed&status=released&milestone=Vaadin+CDI+1.0.0.beta1
  for the complete list of changes.

If any VaadinServlet is explicitly configured either with @WebServlet or
with web.xml, automatic deployment of a CDI servlet will not take place.
In such cases, you should register a VaadinCDIServlet either for "/*" or
for "/mypath/*" and "/VAADIN/*"

To use a custom servlet class, it is recommended to inherit VaadinCDIServlet.
Overriding createServletService() is possible, in which case the service
should inherit VaadinCDIServletService if possible or duplicate its
functionality otherwise.

Changes in 1.0.0.alpha3
-----------------------

- Reintroduce conventions for mapping of views and UIs
  - This requires updating @CDIUI and @CDIView parameter values in existing
    applications.
  - see http://dev.vaadin.com/ticket/12385 for details
- @UIScoped is inherited by subclasses of an annotated UI
- Support non-JEE containers with Weld (BeanManager look-up)
- Use BeanManager.resolve() to support @Alternative UIs
- Partial passivation support
- Automatically injected servlet has async-supported enabled
- Reduced unnecessary logging
- See http://dev.vaadin.com/query?&status=released&milestone=Vaadin+CDI+1.0.0.alpha3
  for the complete list of changes.


Copyright 2012-2014 Vaadin Ltd.

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.