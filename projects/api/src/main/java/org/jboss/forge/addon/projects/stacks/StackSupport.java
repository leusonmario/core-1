/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.addon.projects.stacks;

/**
 * Classes implementing this interface may declare if they support the given {@link Stack}
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
@FunctionalInterface
public interface StackSupport
{
   /**
    * @param stack the stack to be tested upon
    * @return <code>true</code> if this object supports the given {@link Stack}
    */
   boolean supports(Stack stack);
}
