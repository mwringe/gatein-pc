/*
* JBoss, a division of Red Hat
* Copyright 2008, Red Hat Middleware, LLC, and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/

package org.gatein.pc.api;

import static org.jboss.unit.api.Assert.*;
import org.jboss.unit.api.pojo.annotations.Test;

/**
 * @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a>
 * @version $Revision$
 */
public class PortletContextTestCase
{
   @Test
   public void testGetComponents()
   {
      PortletContext context = PortletContext.createPortletContext("/applicationName.portletName");
      assertEquals("applicationName", context.getApplicationName());
      assertEquals("portletName", context.getPortletName());
      assertEquals("/applicationName.portletName", context.getId());

      context = PortletContext.createPortletContext("\t\t\n    /applicationName.portletName   \t");
      assertEquals("applicationName", context.getApplicationName());
      assertEquals("portletName", context.getPortletName());
      assertEquals("/applicationName.portletName", context.getId());

      context = PortletContext.createPortletContext("/");
      assertNull(context.getApplicationName());
      assertNull(context.getPortletName());
      assertEquals("/", context.getId());

      context = PortletContext.createPortletContext("applicationName.portletName");
      assertNull(context.getApplicationName());
      assertNull(context.getPortletName());
      assertEquals("applicationName.portletName", context.getId());

      context = PortletContext.createPortletContext("/applicationName.portlet.Name");
      assertEquals("applicationName", context.getApplicationName());
      assertEquals("portlet.Name", context.getPortletName());
      assertEquals("/applicationName.portlet.Name", context.getId());

      context = PortletContext.createPortletContext("/.");
      assertEquals("", context.getApplicationName());
      assertEquals("", context.getPortletName());
      assertEquals("/.", context.getId());

      context = PortletContext.createPortletContext("/  applicationName\t.  portlet Name");
      assertEquals("applicationName", context.getApplicationName());
      assertEquals("portlet Name", context.getPortletName());
      assertEquals("/applicationName.portlet Name", context.getId());
   }

   @Test
   public void testCreateFromNullOrEmpty()
   {
      try
      {
         PortletContext.createPortletContext(null);
         fail("Attempting to create a PortletContext from null should have thrown an exception.");
      }
      catch (IllegalArgumentException e)
      {
         // expected
      }

      try
      {
         PortletContext.createPortletContext("");
         fail("Attempting to create a PortletContext from empty String should have thrown an exception.");
      }
      catch (IllegalArgumentException e)
      {
         // expected
      }
   }
}
