package austeretony.oxygen_exchange.client.gui.exchange.callback;

import austeretony.alternateui.screen.callback.AbstractGUICallback;
import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.gui.elements.OxygenCallbackGUIFiller;
import austeretony.oxygen_core.client.gui.elements.OxygenGUIButton;
import austeretony.oxygen_core.client.gui.elements.OxygenGUIText;
import austeretony.oxygen_core.client.gui.settings.GUISettings;
import austeretony.oxygen_exchange.client.ExchangeManagerClient;
import austeretony.oxygen_exchange.client.gui.exchange.ExchangeGUISection;
import austeretony.oxygen_exchange.client.gui.exchange.ExchangeMenuGUIContainer;

public class ConfirmationGUICallback extends AbstractGUICallback {

    private final ExchangeMenuGUIContainer screen;

    private final ExchangeGUISection section;

    private OxygenGUIButton confirmButton, cancelButton;

    public ConfirmationGUICallback(ExchangeMenuGUIContainer screen, ExchangeGUISection section, int width, int height) {
        super(screen, section, width, height);
        this.screen = screen;
        this.section = section;
    }

    @Override
    public void init() {
        this.addElement(new OxygenCallbackGUIFiller(0, 0, this.getWidth(), this.getHeight()));
        this.addElement(new OxygenGUIText(4, 5, ClientReference.localize("oxygen_exchange.gui.exchange.confirmCallback"), GUISettings.get().getTitleScale(), GUISettings.get().getEnabledTextColor()));
        this.addElement(new OxygenGUIText(6, 17, ClientReference.localize("oxygen_exchange.gui.exchange.confirmCallback.request"), GUISettings.get().getSubTextScale(), GUISettings.get().getEnabledTextColor()));        

        this.addElement(this.confirmButton = new OxygenGUIButton(15, this.getHeight() - 12, 40, 10, ClientReference.localize("oxygen.gui.confirmButton")));
        this.addElement(this.cancelButton = new OxygenGUIButton(this.getWidth() - 55, this.getHeight() - 12, 40, 10, ClientReference.localize("oxygen.gui.cancelButton")));
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {
        if (mouseButton == 0) {
            if (element == this.cancelButton)
                this.close();
            else if (element == this.confirmButton) {
                ExchangeManagerClient.instance().getExchangeOperationsManager().confirmExchangeSynced();
                this.close();
            }
        }
    }
}