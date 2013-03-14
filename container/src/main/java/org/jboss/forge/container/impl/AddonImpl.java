package org.jboss.forge.container.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

import org.jboss.forge.container.addons.Addon;
import org.jboss.forge.container.addons.AddonDependency;
import org.jboss.forge.container.addons.AddonId;
import org.jboss.forge.container.addons.Status;
import org.jboss.forge.container.lock.LockManager;
import org.jboss.forge.container.repositories.AddonRepository;
import org.jboss.forge.container.services.ServiceRegistry;
import org.jboss.forge.container.util.Assert;
import org.jboss.modules.Module;

public class AddonImpl implements Addon
{
   @SuppressWarnings("unused")
   private final LockManager lock;
   private final AddonId entry;
   private final Set<AddonDependency> dependencies;
   private Status status = Status.MISSING;

   private Module module;
   private ServiceRegistry registry;
   private Set<AddonDependency> missingDependencies = new HashSet<AddonDependency>();
   private Future<Addon> future;
   private AddonRunnable runnable;
   private AddonRepository repository;

   public AddonImpl(LockManager lock, AddonId entry)
   {
      this(lock, entry, null, Collections.<AddonDependency> emptySet());
   }

   public AddonImpl(LockManager lock, AddonId entry, AddonRepository repository, Set<AddonDependency> dependencies)
   {
      Assert.notNull(lock, "LockManager must not be null.");
      Assert.notNull(entry, "AddonId must not be null.");
      Assert.notNull(dependencies, "Dependencies must not be null.");

      this.lock = lock;
      this.entry = entry;
      this.dependencies = dependencies;
      this.repository = repository;
   }

   @Override
   public AddonId getId()
   {
      return entry;
   }

   @Override
   public Set<AddonDependency> getDependencies()
   {
      return dependencies;
   }

   @Override
   public ClassLoader getClassLoader()
   {
      return module.getClassLoader();
   }

   public Module getModule()
   {
      return module;
   }

   public Addon setModule(Module module)
   {
      this.module = module;
      return this;
   }

   @Override
   public AddonRepository getRepository()
   {
      return repository;
   }

   public void setRepository(AddonRepository repository)
   {
      this.repository = repository;
   }

   @Override
   public ServiceRegistry getServiceRegistry()
   {
      return registry;
   }

   public Addon setServiceRegistry(ServiceRegistry registry)
   {
      Assert.notNull(registry, "Registry must not be null.");
      this.registry = registry;
      return this;
   }

   @Override
   public Status getStatus()
   {
      return status;
   }

   public Addon setStatus(Status status)
   {
      Assert.notNull(status, "Status must not be null.");
      this.status = status;
      return this;
   }

   public void setMissingDependencies(Set<AddonDependency> missingDependencies)
   {
      Assert.notNull(missingDependencies, "Missing dependencies must not be null.");
      this.missingDependencies = missingDependencies;
   }

   public Set<AddonDependency> getMissingDependencies()
   {
      return missingDependencies;
   }

   public Future<Addon> getFuture()
   {
      return future;
   }

   public void setFuture(Future<Addon> future)
   {
      Assert.notNull(future, "Future must not be null.");
      this.future = future;
   }

   public AddonRunnable getRunnable()
   {
      return runnable;
   }

   public void setRunnable(AddonRunnable runnable)
   {
      Assert.notNull(runnable, "Runnable must not be null.");
      this.runnable = runnable;
   }

   @Override
   public String toString()
   {
      return getId().toCoordinates() + (status == null ? "" : " - " + status);
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((entry == null) ? 0 : entry.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      AddonImpl other = (AddonImpl) obj;
      if (entry == null)
      {
         if (other.entry != null)
            return false;
      }
      else if (!entry.equals(other.entry))
         return false;
      return true;
   }

}
