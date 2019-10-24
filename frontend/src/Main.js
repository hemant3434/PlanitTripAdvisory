import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Itinerary from './pages/Itinerary';
import DateTime from './pages/DateTime';

import MapPicker from './pages/MapPicker';

import Multi from './components/Navigation/CreateItinerary';

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
        <Text> ProfileScreen </Text>
      </View>
    );
  }
}


const bottomTabNavigator = createBottomTabNavigator(
  {
    Explore: {
      screen: Multi,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="search" size={25} color={tintColor} />
        )
      }
    },
    Map: {
      screen: MapPicker,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="map" size={25} color={tintColor} />
        )
      }
    },
    Trips: {
      screen: TripsScreen,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="paper-plane" size={25} color={tintColor} />
        )
      }
    },
    Itinerary: {
      screen: Itinerary,
      navigationOptions: {
        tabBarIcon: ({ tintColor }) => (
          <Icon name="search" size={25} color={tintColor} />
        )
      }
    },
    Profile: {
      screen: ProfileScreen,
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
      activeTintColor: '#F6828C'
    }
  }
);

const AppContainer = createAppContainer(bottomTabNavigator);
