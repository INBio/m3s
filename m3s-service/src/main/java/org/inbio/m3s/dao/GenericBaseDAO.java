/* M3S - multimedia management system
*
* Copyright (C) 2009  INBio - Instituto Nacional de Biodiversidad, Costa Rica
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.inbio.m3s.dao;

import java.util.List;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * @author jgutierrez
 *
 */
public interface GenericBaseDAO<E extends LogGenericEntity,I extends Object> {
 
 /**
    *
    * @param entity
    */
   public void create(E entity);


   /**
    *
    * @param entity
    */
   public void delete(E entity);

   /**
    *
    * @param entity
    */
   public void update(E entity);

   /**
    *
    * @param entityClass
    * @param entityId
    * @return
    */
   public E findById(Class<E> entityClass, I entityId);


   /**
    *
    * @param entityClass
    * @return
    */
   public List<E> findAll(Class<E> entityClass);


   /**
    *
    * @param entityClass
    * @param firstResult
    * @param maxResults
    * @return
    */
   //public List<E> findAllPaginated(Class<E> entityClass,int firstResult, int maxResults);


   /**
    * 
    * @param entityClass
    * @return
    */
   //public Long count(Class<E> entityClass);

}
