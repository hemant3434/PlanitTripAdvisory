import React from 'react';
import { StyleSheet, View  } from 'react-native';
import {
  Container,
  Header,
  Title,
  Content,
  Button,
  Icon,
  List,
  ListItem,
  Text,
  Left,
  Right,
  Body
} from "native-base";
import ImageCard from './../components/Cards/ImageCard';

export default function CardsContainer() {
  return (
    <View style={styles.container}>
      <Text>CARDS GO HERE!</Text>
      <Content>
        <List>
          <ListItem>
            <ImageCard title="Toronto" image={require("../images/toronto.jpg")} text="lmao"/>
            <ImageCard title="Scarborough" image={require("../images/toronto.jpg")} text="HAHA"/>
          </ListItem>
        </List>
      </Content>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
