/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.addon.environment;

import java.util.Map;

import org.jboss.forge.furnace.services.Exported;

/**
 * An {@link Environment} stores information separated into categories
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 *
 */
@Exported
public interface Environment
{

   /**
    * Returns a mutable map that contains the properties for a specific {@link Category}
    *
    * @param key
    * @return
    */
   <K, V> Map<K, V> get(Class<? extends Category> key);
}
