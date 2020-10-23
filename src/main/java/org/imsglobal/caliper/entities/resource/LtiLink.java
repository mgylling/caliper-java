/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.entities.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.resource.LtiMessageType;
import org.imsglobal.caliper.entities.EntityType;

public class LtiLink extends AbstractDigitalResource {

    @JsonProperty("messageType")
    private final LtiMessageType messageType;

    /**
     * @param builder apply builder object properties to the LtiLink object.
     */
    private LtiLink(Builder<?> builder) {
        super(builder);
        this.messageType = builder.messageType;
    }

    public LtiMessageType getMessageType() {
        return messageType;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractDigitalResource.Builder<T> {
        private LtiMessageType messageType;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.LTI_LINK);
        }

        public T messageType(LtiMessageType messageType) {
            this.messageType = messageType;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the LtiLink.
         */
        public LtiLink build() {
            return new LtiLink(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}