import { TutorialPage } from './tutorial/tutorial';
import { CommunityChatPage } from './community-chat/community-chat';
import { CommunityProfilPage } from './community-profil/community-profil';
import { CommunityUgalleryPage } from './community-ugallery/community-ugallery';
import { WissenBeliebtPage } from './wissen-beliebt/wissen-beliebt';
import { WissenNeuPage } from './wissen-neu/wissen-neu';
import { WissenTopPage } from './wissen-top/wissen-top';
import { WissenExpertenPage } from './wissen-experten/wissen-experten';
import { WissenSuchePage } from './wissen-suche/wissen-suche';
import { MapPage } from './map/map';

// The page the user lands on after opening the app and without a session
export const FirstRunPage = TutorialPage;

// The main page the user will see as they use the app over a long period of time.
// Change this if not using tabs
export const MainPage = MapPage;

// The initial root pages for our tabs (remove if not using tabs)
export const Tab1Root = WissenBeliebtPage;
export const Tab2Root = WissenNeuPage;
export const Tab3Root = WissenTopPage;
export const Tab4Root = WissenExpertenPage;
export const Tab5Root = WissenSuchePage;
export const Tab6Root = CommunityProfilPage;
export const Tab7Root = CommunityChatPage;
export const Tab8Root = CommunityUgalleryPage;
