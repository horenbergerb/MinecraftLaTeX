/*
 * Copyright or © or Copr. Moribus (2013)
 * Copyright or © or Copr. ProkopyL <prokopylmc@gmail.com> (2015)
 * Copyright or © or Copr. Amaury Carrade <amaury@carrade.eu> (2016 – 2021)
 * Copyright or © or Copr. Vlammar <valentin.jabre@gmail.com> (2019 – 2021)
 *
 * This software is a computer program whose purpose is to allow insertion of
 * custom images in a Minecraft world.
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

package fr.moribus.imageonmap;


import fr.moribus.imageonmap.commands.maptool.DeleteCommand;
import fr.moribus.imageonmap.commands.maptool.ExploreCommand;
import fr.moribus.imageonmap.commands.maptool.GetCommand;
import fr.moribus.imageonmap.commands.maptool.GetRemainingCommand;
import fr.moribus.imageonmap.commands.maptool.GiveCommand;
import fr.moribus.imageonmap.commands.maptool.ListCommand;
import fr.moribus.imageonmap.commands.maptool.NewCommand;
import fr.moribus.imageonmap.commands.maptool.RenameCommand;
import fr.moribus.imageonmap.commands.maptool.UpdateCommand;
import fr.moribus.imageonmap.image.ImageIOExecutor;
import fr.moribus.imageonmap.image.ImageRendererExecutor;
import fr.moribus.imageonmap.image.MapInitEvent;
import fr.moribus.imageonmap.map.MapManager;
import fr.moribus.imageonmap.ui.MapItemManager;
import fr.zcraft.quartzlib.components.commands.CommandWorkers;
import fr.zcraft.quartzlib.components.commands.Commands;
import fr.zcraft.quartzlib.components.gui.Gui;
import fr.zcraft.quartzlib.components.i18n.I18n;
import fr.zcraft.quartzlib.core.QuartzPlugin;
import fr.zcraft.quartzlib.tools.PluginLogger;
import fr.zcraft.quartzlib.tools.UpdateChecker;
import java.io.File;
import java.io.IOException;
import org.bstats.bukkit.Metrics;

<<<<<<< HEAD
public final class ImageOnMap extends QuartzPlugin
{
    static private final String IMAGES_DIRECTORY_NAME = "images";
    static private final String LATEX_DIRECTORY_NAME = "latex";
    static private final String MAPS_DIRECTORY_NAME = "maps";
    static private ImageOnMap plugin;
    private File imagesDirectory;
    private File latexDirectory;
=======
public final class ImageOnMap extends QuartzPlugin {
    private static final String IMAGES_DIRECTORY_NAME = "images";
    private static final String MAPS_DIRECTORY_NAME = "maps";
    private static ImageOnMap plugin;
>>>>>>> 900cc7512b299f6cdcbe46b9c00a690a46675898
    private final File mapsDirectory;
    private File imagesDirectory;
    private CommandWorkers commandWorker;

    public ImageOnMap() {
        imagesDirectory = new File(this.getDataFolder(), IMAGES_DIRECTORY_NAME);
        mapsDirectory = new File(this.getDataFolder(), MAPS_DIRECTORY_NAME);
        latexDirectory = new File(this.getDataFolder(), LATEX_DIRECTORY_NAME);
        plugin = this;
    }

    public static ImageOnMap getPlugin() {
        return plugin;
    }
<<<<<<< HEAD
    
    public File getImagesDirectory() {return imagesDirectory;}
    public File getMapsDirectory() {return mapsDirectory;}
    public File getLatexDirectory() {return latexDirectory;}
    public File getImageFile(int mapID)
    {
        return new File(imagesDirectory, "map"+mapID+".png");
=======

    public File getImagesDirectory() {
        return imagesDirectory;
    }

    public File getMapsDirectory() {
        return mapsDirectory;
    }

    public File getImageFile(int mapID) {
        return new File(imagesDirectory, "map" + mapID + ".png");
    }

    public CommandWorkers getCommandWorker() {
        return commandWorker;
>>>>>>> 900cc7512b299f6cdcbe46b9c00a690a46675898
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onEnable() {
        // Creating the images and maps directories if necessary
        try {
            //imagesDirectory = checkPluginDirectory(imagesDirectory, V3Migrator.getOldImagesDirectory(this));
            checkPluginDirectory(mapsDirectory);
<<<<<<< HEAD
            checkPluginDirectory(latexDirectory);
        }
        catch(final IOException ex)
        {
=======
            checkPluginDirectory(imagesDirectory);
        } catch (final IOException ex) {
>>>>>>> 900cc7512b299f6cdcbe46b9c00a690a46675898
            PluginLogger.error("FATAL: " + ex.getMessage());
            this.setEnabled(false);
            return;
        }


        saveDefaultConfig();
        commandWorker = loadComponent(CommandWorkers.class);
        loadComponents(I18n.class, Gui.class, Commands.class, PluginConfiguration.class, ImageIOExecutor.class,
                ImageRendererExecutor.class);

        //Init all the things !
        I18n.setPrimaryLocale(PluginConfiguration.LANG.get());

        MapManager.init();
        MapInitEvent.init();
        MapItemManager.init();


        Commands.register(
                "maptool",
                LatexCommand.class,
                NewCommand.class,
                ListCommand.class,
                GetCommand.class,
                RenameCommand.class,
                DeleteCommand.class,
                GiveCommand.class,
                GetRemainingCommand.class,
                ExploreCommand.class,
                //MigrateCommand.class,//Removed for now doesn't work nor is useful, maybe useful later on
                UpdateCommand.class
        );

        Commands.registerShortcut("maptool", LatexCommand.class, "latex");
        Commands.registerShortcut("maptool", NewCommand.class, "tomap");
        Commands.registerShortcut("maptool", ExploreCommand.class, "maps");
        Commands.registerShortcut("maptool", GiveCommand.class, "givemap");

        if (PluginConfiguration.CHECK_FOR_UPDATES.get()) {
            UpdateChecker.boot("imageonmap.26585");
        }

        if (PluginConfiguration.COLLECT_DATA.get()) {
            final Metrics metrics = new Metrics(this,5920);
            metrics.addCustomChart(new Metrics.SingleLineChart("rendered-images", MapManager::getImagesCount));
            metrics.addCustomChart(new Metrics.SingleLineChart("used-minecraft-maps", MapManager::getMapCount));
        } else {
            PluginLogger.warning("Collect data disabled");
        }
    }

    @Override
    public void onDisable() {
        MapManager.exit();
        MapItemManager.exit();
        //MigratorExecutor.waitForMigration();//Removed for now doesn't work nor is useful, maybe useful later on

        super.onDisable();
    }

    private File checkPluginDirectory(File primaryFile, File... alternateFiles) throws IOException {
        if (primaryFile.exists()) {
            return primaryFile;
        }
        for (File file : alternateFiles) {
            if (file.exists()) {
                return file;
            }
        }
        if (!primaryFile.mkdirs()) {
            throw new IOException("Could not create '" + primaryFile.getName() + "' plugin directory.");
        }
        return primaryFile;
    }
}
