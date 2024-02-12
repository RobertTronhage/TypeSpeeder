/** MenuService.java
 * This class interface is implemented by all menus.
 * Author:Robert Tronhage, robert.tronhage@iths.se
 * Date 2024-02-12
 */


package se.ju23.typespeeder.menu;

import java.util.List;

public interface MenuService {
    List getMenuOptions();

    void displayMenu();
}
