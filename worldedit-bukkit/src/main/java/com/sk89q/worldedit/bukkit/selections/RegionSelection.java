/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.bukkit.selections;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import org.bukkit.Location;
import org.bukkit.World;

import static com.sk89q.worldedit.bukkit.BukkitUtil.toLocation;
import static com.sk89q.worldedit.bukkit.BukkitUtil.toVector;

public abstract class RegionSelection implements Selection {

    private World world;
    private RegionSelector selector;
    private Region region;

    public RegionSelection(World world) {
        this.world = world;
    }

    public RegionSelection(World world, RegionSelector selector, Region region) {
        this.world = world;
        this.region = region;
        this.selector = selector;
    }

    protected Region getRegion() {
        return region;
    }

    protected void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public RegionSelector getRegionSelector() {
        return selector;
    }

    protected void setRegionSelector(RegionSelector selector) {
        this.selector = selector;
    }

    @Override
    public Location getMinimumPoint() {
        return toLocation(world, region.getMinimumPoint());
    }

    @Override
    public Vector getNativeMinimumPoint() {
        return region.getMinimumPoint();
    }

    @Override
    public Location getMaximumPoint() {
        return toLocation(world, region.getMaximumPoint());
    }

    @Override
    public Vector getNativeMaximumPoint() {
        return region.getMaximumPoint();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public int getArea() {
        return region.getArea();
    }

    @Override
    public int getWidth() {
        return region.getWidth();
    }

    @Override
    public int getHeight() {
        return region.getHeight();
    }

    @Override
    public int getLength() {
        return region.getLength();
    }

    @Override
    public boolean contains(Location position) {
        if (!position.getWorld().equals(world)) {
            return false;
        }

        return region.intersectsBlock(toVector(position));
    }

}
