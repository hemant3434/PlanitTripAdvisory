import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import MapPage from './pages/MapPage';
import ExplorePage from './pages/ExplorePage';
import ItineraryPage from './pages/ItineraryPage';
import CreateItinerary from './navigation/CreateItinerary';
import LoadingScreen from './pages/LoadingScreen';
import ProfilePage from './pages/ProfilePage';
import TripsPage from './pages/TripsPage';

import { createAppContainer } from 'react-navigation';
import { createBottomTabNavigator } from 'react-navigation-tabs';
import Icon from "react-native-vector-icons/FontAwesome";

export default class HybridApp extends React.Component {
  render() {
    return (
      <AppContainer />
    );
  }
}

class ExploreScreen extends React.Component {
  render() {
    return(
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        <Text> ExploreScreen </Text>
      </View>
    );
  }
}

class TripsScreen extends React.Component {
  render() {
    return(
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#fff'}}>
        <Text> TripsScreen </Text>
      </View>
    );
  }
}

class ProfileScreen extends React.Component {
  render() {
    return(
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#d0d0d0'}}>
        <Text> ProfileScreen</Text>
      </View>
    );
  }
}

const bottomTabNavigator = createBottomTabNavigator(
  {
    Explore: {
      screen: ExplorePage,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="search" size={25} color={tintColor} />
        )
      }
    },
    Map: {
      screen: MapPage,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="map" size={25} color={tintColor} />
        )
      }
    },
    Trips: {
      screen: TripsPage,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="paper-plane" size={25} color={tintColor} />
        )
      }
    },
    Itinerary: {
      screen: ItineraryPage,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="search" size={25} color={tintColor} />
        )
      }
    },
    Profile: {
      screen: ProfilePage,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="user" size={25} color={tintColor} />
        )
      }
    },
  },
  {
    initialRouteName: 'Explore',
    tabBarOptions: {
      activeTintColor: '#600EE6'
    }
  }
);

const AppContainer = createAppContainer(bottomTabNavigator);
