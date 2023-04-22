import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.inject.Inject;

@PluginDescriptor(
    name = "Item Spell Caster",
    description = "Casts a spell on a certain item in your inventory"
)
public class ItemSpellCasterPlugin extends Plugin {
    private static final String SPELL_NAME = "Lvl-5 Enchant";
    private static final int ITEM_ID = 11115; // Replace with the ID of the item you want to cast the spell on
    private static final int SPELL_WIDGET_ID = WidgetID.SPELLBOOK_MODERN_GROUP_ID;
    private static final int SPELL_COMPONENT_ID = 0;
    private static final int INVENTORY_WIDGET_ID = WidgetInfo.INVENTORY.getDefaultId();
    private static final int INVENTORY_ITEM_OFFSET = 1; // The first item in the inventory has an index of 1

    @Inject
    private Client client;

    @Override
    public void startUp() {
        // Initialize any necessary components here
        // Set up any event listeners
        super.startUp();
    }

    @Override
    public void shutDown() {
        // Clean up any resources here
        // Unregister any event listeners
        super.shutDown();
    }

    @Override
    public void onWidgetLoaded(WidgetLoaded event) {
        // Check if the spellbook widget has loaded
        if (event.getGroupId() == SPELL_WIDGET_ID) {
            // Send a mouse click to the spell component to open the spellbook
            Widget spellWidget = client.getWidget(SPELL_WIDGET_ID, SPELL_COMPONENT_ID);
            spellWidget.interact(MenuAction.WIDGET_CLICK.getId());
        }
    }

    @Override
    public void onMenuOptionClicked(MenuOptionClicked event) {
        // Check if the clicked menu option corresponds to the spell you want to cast
        if (event.getOption().equals(SPELL_NAME)) {
            // Check if the item you want to target is in your inventory
            if (client.getItemContainer(Inventory
